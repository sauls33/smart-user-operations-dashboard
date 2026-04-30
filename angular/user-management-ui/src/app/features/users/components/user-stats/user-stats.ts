import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-user-stats',
  imports: [],
  templateUrl: './user-stats.html',
  styleUrl: './user-stats.css'
})
export class UserStats {
  @Input() total = 0;
  @Input() active = 0;
  @Input() inactive = 0;
}
