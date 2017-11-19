export interface Self {
  href: string;
}

export interface Alert2 {
  href: string;
}

export interface Links {
  self: Self;
  alert: Alert2;
}

export interface Alert {
  query: string;
  createdDate: Date;
  alertStatus: string;
  completedDate?: any;
  _links: Links;
}

export interface Embedded {
  alerts: Alert[];
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

export interface Search {
  href: string;
}

export interface Links2 {
  first: First;
  self: Self2;
  next: Next;
  last: Last;
  profile: Profile;
  search: Search;
}

export interface Page {
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}

export interface AlertResponse {
  _embedded: Embedded;
  _links: Links2;
  page: Page;
}

