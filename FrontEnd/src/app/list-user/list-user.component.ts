import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../service/token-storage.service';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  [x: string]: any;
  //users!: User[];
  token: any;

  /*---- pagination -----*/
  currentSelectedPage:number = 0;
  users: Array<User> = [];
  pageIndexes: Array<number> = [];
  namePage: String = 'listUser';

  searchedKeyword!: string;

  config: any;
  /*---------------------*/


  constructor(private userService : UserService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    //this.retrieveUsers();
    this.getPage();
  }

  retrieveUsers(): void {
    this.token = this.tokenStorage.getToken();

    this.userService.getAll(this.token)
      .subscribe(
        (data: User[]) => {
          this.users = data;
        },
        error => {
          console.log(error);
        });
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  getPage(){
    this.token = this.tokenStorage.getToken();

    console.log(this.userService.getAllUser(this.token));

    this.userService.getAllUser(this.token)
            .subscribe(
                (user: User[]) => {
                  this.users = user;             
                },
                (error) => {
                  console.log(error);
                }
            );

    this.config = {
        itemsPerPage: 5,
        currentPage: 1,
        totalItems: this.users.values.length
    };
  }

  getFirst(){
      this.config.currentPage = 1;
    
  }

  getPaginationWithIndex(index: number) {
      this.getPage();
  }

  active(index: number) {
    if(this.currentSelectedPage == index ){
      return {
        active: true
      };
    }else{
      return {
        active: undefined
      };
    }
  }

  

  deleteUser(username: String): void {
    this.token = this.tokenStorage.getToken();
    console.log(this.token);
    this.userService.deleteUser(this.token, username)
      .subscribe(
        (response: any) => {
          this.reloadPage();
        },
        (error: any) => {
          console.log(error);
        });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
