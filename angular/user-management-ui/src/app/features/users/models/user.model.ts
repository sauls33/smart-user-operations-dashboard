export interface User {
  id?: number;
  name: string;
  email: string;
  active: boolean;

  role: string;
  department: string;
  lastLoginDate?: string;
  riskLevel: string;
}