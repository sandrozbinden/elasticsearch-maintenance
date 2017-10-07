declare module DeleteQueryResponse {
  
      export interface Self {
          href: string;
      }
  
      export interface DeleteQuery {
          href: string;
      }
  
      export interface Links {
          self: Self;
          deleteQuery: DeleteQuery;
      }
  
      export interface Deletequery {
          field: string;
          value: string;
          _links: Links;
      }
  
      export interface Embedded {
          deletequery: Deletequery[];
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
  
      export interface DeleteQueryResponse {
          _embedded: Embedded;
          _links: Links2;
          page: Page;
      }
  
  }
  
  