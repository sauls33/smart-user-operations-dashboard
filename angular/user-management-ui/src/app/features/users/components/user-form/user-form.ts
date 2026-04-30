import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User, UserFormValue } from '../../models/user.model';

@Component({
  selector: 'app-user-form',
  imports: [FormsModule],
  templateUrl: './user-form.html',
  styleUrl: './user-form.css'
})
export class UserForm implements OnChanges {
  @Input() selectedUser: User | null = null;
  @Output() save = new EventEmitter<UserFormValue>();
  @Output() cancelEdit = new EventEmitter<void>();

  readonly name = signal('');
  readonly email = signal('');
  readonly active = signal(true);

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedUser']) {
      this.name.set(this.selectedUser?.name ?? '');
      this.email.set(this.selectedUser?.email ?? '');
      this.active.set(this.selectedUser?.active ?? true);
    }
  }

  submit(): void {
    const value: UserFormValue = {
      name: this.name().trim(),
      email: this.email().trim().toLowerCase(),
      active: this.active()
    };

    if (!value.name || !value.email) return;

    this.save.emit(value);
    if (!this.selectedUser) this.reset();
  }

  cancel(): void {
    this.reset();
    this.cancelEdit.emit();
  }

  private reset(): void {
    this.name.set('');
    this.email.set('');
    this.active.set(true);
  }
}
