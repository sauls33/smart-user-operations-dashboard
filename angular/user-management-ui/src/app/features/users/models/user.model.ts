export interface User {
  id?: number;
  name: string;
  email: string;
  active: boolean;
}

export interface UserFormValue {
  name: string;
  email: string;
  active: boolean;
}