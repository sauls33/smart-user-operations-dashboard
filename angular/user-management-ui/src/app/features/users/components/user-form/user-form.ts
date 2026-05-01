import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-form',
  imports: [FormsModule],
  templateUrl: './user-form.html',
  styleUrl: './user-form.css'
})
export class UserForm {

  @Input() selectedUser: User | null = null;

  @Output() save = new EventEmitter<User>();
  @Output() cancelEdit = new EventEmitter<void>();

  readonly name = signal('');
  readonly email = signal('');
  readonly active = signal(true);
  readonly role = signal('');
  readonly department = signal('');
  readonly lastLoginDate = signal('');
  readonly riskLevel = signal('LOW');

  ngOnChanges() {
    if (this.selectedUser) {
      this.name.set(this.selectedUser.name);
      this.email.set(this.selectedUser.email);
      this.active.set(this.selectedUser.active);
      this.role.set(this.selectedUser.role);
      this.department.set(this.selectedUser.department);
      this.lastLoginDate.set(this.selectedUser.lastLoginDate ?? '');
      this.riskLevel.set(this.selectedUser.riskLevel);
    }
  }

  submit(): void {
    const user: User = {
      id: this.selectedUser?.id,
      name: this.name(),
      email: this.email(),
      active: this.active(),
      role: this.role(),
      department: this.department(),
      lastLoginDate: this.lastLoginDate(),
      riskLevel: this.riskLevel()
    };

    this.save.emit(user);
    this.resetForm();
  }

  resetForm(): void {
    this.name.set('');
    this.email.set('');
    this.active.set(true);
    this.role.set('');
    this.department.set('');
    this.lastLoginDate.set('');
    this.riskLevel.set('LOW');
  }

  cancel(): void {
    this.cancelEdit.emit();
    this.resetForm();
  }
}