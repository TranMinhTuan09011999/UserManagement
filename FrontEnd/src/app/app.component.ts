import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './service/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'jwt-username';

    [x: string]: any;
    userName!: string;

    token = '';
  
    constructor(private tokenStorageService: TokenStorageService) { }
  
    ngOnInit(): void {
    }

    isLoggedIn():boolean{
      this.token = this.tokenStorageService.getToken();
      if(this.token == '{}')
      {
        return false;
        
      }else{    
        const user = this.tokenStorageService.getUser();
        this.userName = user.userName;
        return true;
      }
  }
  
    logout(): void {
      this.tokenStorageService.signOut();
    }
}
