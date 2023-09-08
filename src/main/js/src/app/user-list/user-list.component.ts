import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { Course } from '../model/course';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService) {
  }



  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  saveCourse(u) {
      console.log(u.courseInput);
      u.courses?.push({name: u.courseInput});
      this.userService.save(u).subscribe();

    }
}
