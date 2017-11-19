package ch.zbinden.engineering.elasticsearch.monitoring.web;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;

import ch.zbinden.engineering.elasticsearch.monitoring.domain.IndexFieldJSON;
import ch.zbinden.engineering.elasticsearch.monitoring.service.ElasticSearchService;

@RestController
public class IndexController {

	@Autowired
	private ElasticSearchService esService;

	@RequestMapping("/index/fields")
	public List<IndexFieldJSON> indexFields() {
		return esService.getLogstashIndexMetaDatas() //
				.stream() //
				.map(index -> Sets.newHashSet(index.getMappings().values())) //
				.flatMap(Set::stream) //
				.map(mappingMetaData -> ((Map<String, Object>) mappingMetaData.value.getSourceAsMap().get("properties")).keySet()) //
				.flatMap(Set::stream) //
				.distinct()
				.map(name -> new IndexFieldJSON(name)).collect(Collectors.toList());

	}
	
	@RequestMapping("/index/size")
	public long indexSizeInBytes() {
		return esService.getIndiciesSizeInBytes();

	}
}
