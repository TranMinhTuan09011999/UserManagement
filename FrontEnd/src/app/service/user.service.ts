import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from '../user';
import { TokenStorageService } from './token-storage.service';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  [x: string]: any;
  
  private baseUrl = 'http://localhost:8181/users/';
  private baseUrl_ = 'http://localhost:8181';
  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}

  getAll(token: String): Observable<User[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    console.log(headers);
    return this.http.get<User[]>(this.baseUrl, {headers});
  }
  /*--------------------------------------------------------------*/

  getAllUser(token: String): Observable<User[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);

    // Initialize Params Object
    return this.http.get<User[]>(`${this.baseUrl}`, { headers: headers})
                  .pipe(
                  retry(3),
                    catchError(this.handleError)
                  );
    
  }

  getAllUserBy(token: String, field: String ,username: String): Observable<User[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    // Initialize Params Object
    return this.http.get<User[]>(`${this.baseUrl}${field}/${username}`, { headers: headers})
                  .pipe(
                  retry(3),
                    catchError(this.handleError)
                  );
    
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
  /*-------------- Delete User by username ---------------*/
  deleteUser(token: String,username: String) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    let delete_ = 'delete/';

    // Initialize Params Object
    let params = new HttpParams();
    params = params.append('username', username.toString());
    return this.http.delete(`${this.baseUrl}${delete_}`, { headers: headers, params: params });
  }
  /*------------------ Ckeck email already existed ------------------- */
  doesEmailExist(email: string): Observable<boolean> {
    let url = `${this.baseUrl_}/emailcheck`;

    let content: any = {};
    content.email = email;

    let response$: Observable<boolean> = this.http.post<boolean>(url, content);

    return response$;
  }
  /*------------------ Ckeck username already existed ------------------- */
  doesUsernameExist(username: string): Observable<boolean> {
    let url = `${this.baseUrl_}/usernamecheck`;

    let content: any = {};
    content.userName = username;

    let response$: Observable<boolean> = this.http.post<boolean>(url, content);

    return response$;
  }
  /*------------------- Create User --------------------*/
  createData(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl_}/register`, info);
  }
}

