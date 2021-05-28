import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { AuthService } from '../service/auth.service';
import { TokenStorageService } from '../service/token-storage.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  namePage: String = 'listUser';

  dataForm!: FormGroup;
  IsLogin = false;

  constructor(public crudApi: UserService, private authService: AuthService, private tokenStorage: TokenStorageService, private router:Router, public fb: FormBuilder) { }

  ngOnInit(): void {
      this.infoForm();
  }

  infoForm(){
    /*Create Form group*/
    this.dataForm = this.fb.group({
      userName: ['', [Validators.required], [this.usernameExistsValidator()]],   
      password: ['', [Validators.required]]
    })
  }
  
  private usernameExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return of(control.value).pipe(
        delay(500),
        switchMap((username: any) => this.crudApi.doesUsernameExist(username).pipe(
          map(userExists => !userExists ? { userExists: true } : null)
        ))
      );
    };
  }

  onSubmit(): void {
    this.authService.getAccessToken(this.dataForm.value).subscribe(
      data => {
        this.tokenStorage.saveUser(this.dataForm.value);
        this.tokenStorage.saveToken(data);
        this.router.navigate([`${this.namePage}`]);
      },
      err => {  
        this.IsLogin = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
