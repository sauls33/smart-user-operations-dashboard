import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css'
})
export class UserList {
  @Input({ required: true }) users: User[] = [];
  @Input() loading = false;

  @Output() edit = new EventEmitter<User>();
  @Output() remove = new EventEmitter<User>();
}