import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { getApiBaseUrl } from '../../config/api-url';

@Injectable({
  providedIn: 'root'
})
export class AiService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${getApiBaseUrl()}/api/ai/user-summary`;

  generateSummary(): Observable<{ summary: string }> {
    return this.http.post<{ summary: string }>(this.apiUrl, {});
  }
}