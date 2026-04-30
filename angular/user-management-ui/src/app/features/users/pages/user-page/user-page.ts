import { Component, computed, inject, OnInit, signal } from '@angular/core';

import { UserApiService } from '../../../../core/services/user-api.service';
import { User } from '../../models/user.model';
import { UserForm } from '../../components/user-form/user-form';
import { UserList } from '../../components/user-list/user-list';
import { UserStats } from '../../components/user-stats/user-stats';

@Component({
  selector: 'app-user-page',
  imports: [UserForm, UserList, UserStats],
  templateUrl: './user-page.html',
  styleUrl: './user-page.css'
})
export class UserPage implements OnInit {
  private readonly userApiService = inject(UserApiService);

  readonly users = signal<User[]>([]);
  readonly selectedUser = signal<User | null>(null);
  readonly loading = signal(false);
  readonly error = signal('');

  readonly apiUsersUrl = this.userApiService.getApiUrl();

  readonly totalCount = computed(() => this.users().length);
  readonly activeCount = computed(() => this.users().filter(user => user.active).length);
  readonly inactiveCount = computed(() => this.users().filter(user => !user.active).length);

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.loading.set(true);
    this.error.set('');

    this.userApiService.getUsers().subscribe({
      next: (users: User[]) => {
        this.users.set(users);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Could not load users. Make sure Spring Boot is running and port 8080 is public in Codespaces.');
        this.loading.set(false);
      }
    });
  }

  saveUser(user: User): void {
    const request = this.selectedUser()
      ? { ...user, id: this.selectedUser()?.id }
      : user;

    if (request.id) {
      this.userApiService.updateUser(request.id, request).subscribe({
        next: () => {
          this.selectedUser.set(null);
          this.loadUsers();
        },
        error: () => this.error.set('Could not update user.')
      });

      return;
    }

    this.userApiService.createUser(request).subscribe({
      next: () => {
        this.selectedUser.set(null);
        this.loadUsers();
      },
      error: () => this.error.set('Could not create user.')
    });
  }

  editUser(user: User): void {
    this.selectedUser.set({ ...user });
  }

  cancelEdit(): void {
    this.selectedUser.set(null);
  }

  deleteUser(user: User): void {
    if (!user.id) return;

    this.userApiService.deleteUser(user.id).subscribe({
      next: () => this.loadUsers(),
      error: () => this.error.set('Could not delete user.')
    });
  }
}