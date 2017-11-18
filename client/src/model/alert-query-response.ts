export interface Self {
    href: string;
}

export interface AlertQuery {
    href: string;
}

export interface Links {
    self: Self;
    alertQuery: AlertQuery;
}

export interface Alertquery {
    field: string;
    value: string;
    _links?: Links;
}

export interface Embedded {
    alertQueries: Alertquery[];
}

export interface First {
    href: string;
}

export interface Self2 {
    href: string;
    templated: boolean;
}

export interface Next {
    href: string;
}

export interface Last {
    href: string;
}

export interface Profile {
    href: string;
}

export interface Links2 {
    first: First;
    self: Self2;
    next: Next;
    last: Last;
    profile: Profile;
}

export interface Page {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
}

export interface AlertQueryResponse {
    _embedded: Embedded;
    _links: Links2;
    page: Page;
}
