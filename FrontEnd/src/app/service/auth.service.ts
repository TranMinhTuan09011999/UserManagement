import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtClientService } from '../jwt-client.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  response:any;

  constructor(private http: HttpClient, private service:JwtClientService) { }

  public getAccessToken(authRequest: any){
    let resp=this.service.generateToken(authRequest);
    //resp.subscribe(data=>this.accessApi(data));
    return resp;
      }
    
  public accessApi(token: string){
    let resp=this.service.welcome(token);
    resp.subscribe(data=>this.response=data);
      }
}
