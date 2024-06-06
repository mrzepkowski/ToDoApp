<h2>Description</h2>
To-do app is an application that allows user to manage their to-do items. The main goal of this project<br>
is to create an user-friendly tool that makes it easier to plan and manage their activities. For this<br>
purpose, the application is intended to implement the following functionalities:
<ul>
  <li>Managing many to-do lists:
    <ul>
    <li>Creating to-do list, assigning to a specific color,</li>
    <li>Viewing to-do list's content (to-do items belonging to it)</li>
    <li>Deleting to-do list;</li>
    </ul>
  </li>
  <li>Managing to-do items on to-do lists:
    <ul>
    <li>Creating to-do item with non-empty content and deadline (optional),</li>
    <li>Managing to-do item: edit content, edit deadline, mark as completed, mark as starred,</li>
    <li>Deleting to-do item;</li>
    </ul>
  </li>
  <li>Showing to-do items in several forms (sorted by to-do lists):
    <ul>
    <li>On specific to-do list,</li>
    <li>All to-do items,</li>
    <li>To-do items with today deadline</li>
    <li>Starred to-do items,</li>
    <li>Completed to-do items</li>
    </ul>
  </li>
</ul>
<br>
  
<h2>Tech stack</h2>
<ul>
<li>Database
  <ul>
  <li>DB management system – MySQL</li>
  <li>Hosting – AWS RDS</li>
  </ul>
</li>
<li>Backend (Rest API)
  <ul>
  <li>Java 21</li>
  <li>Spring Boot 3.2.4</li>
  <li>Spring Security 6.2.3</li>
  <li>Spring Data JPA</li>
  <li>JJWT 0.12.5</li>
  </ul>
</li>
<li>(in the future) Frontend – Angular/React.js</li>
</ul>
<br>

<h2>API structure</h2>
Accepted request body format: JSON<br>
<br>
<b>Authentication (/api/auth):</b>
<ul>
  <li>/register – POST – perform register operation<br>
  Accepted request body format:<br>
  {<br>
    "username": "{username}",<br>
    "email": "{email}",<br>
    "password": "{password}"<br>
  }<br>
  Returned response body:<br>
  {<br>
    "username": "{username}",<br>
    "email": "{email}"<br>
  }</li>
  <li>/login – POST – perform login operation<br>
  Accepted request body format:<br>
  {<br>
    "username": "{username}",<br>
    "password": "{password}"<br>
  }<br>
  Returned response body:<br>
  {<br>
    "message": "User logged in successfully",<br>
    "token": "{JWT token}"<br>
  }</li>
  <li>/logout – POST – perform logout operation</li>
</ul>
<b>User (/api/v1/users):</b>
<ul>
  <li>/ – GET – returns data of currently logged in user<br>
  Returned response body:<br>
  {<br>
    "username": "{username}",<br>
    "email": "{email}"<br>
  }</li>
  <li>/change-email – PATCH – performs email change operation for currently logged in user<br>
  Accepted request body format:<br>
  {<br>
    "oldEmail": "{old email}",<br>
    "newEmail": "{new email}",<br>
    "password": "{password}"<br>
  }<br>
  Returned response body:<br>
  {<br>
    "username": "{username}",<br>
    "email": "{email}"<br>
  }</li>
  <li>/change-email – PATCH – performs password change operation for currently logged in user<br>
  Accepted request body format:<br>
  {<br>
    "oldPassword": "{old password}",<br>
    "newPassword": "{new password}"<br>
  }<br>
  Returned response body:<br>
  {<br>
    "username": "{username}",<br>
    "email": "{email}"<br>
  }</li>
</ul>
<b>Color (/api/v1/colors):</b>
<ul>
  <li>/all – GET – returns list of Color entities</li>
  <li>/{id} – GET – returns Color entity by id</li>
  <li>/by-name/{color-name} – GET – returns color entity by its name</li>
</ul>
<b>ToDoList (/api/v1/todolists):</b>
<ul>
  <li>/all – GET – returns list of all ToDoList entities for currently logged in user</li>
  <li>/{id} – GET – returns ToDoList entity by id for currently logged in user</li>
  <li>/by-color-id/{color-id} – GET – returns list of all ToDoList entities by color-id for currently logged in user</li>
  <li>/ – creates ToDoList entity for currently logged in user<br>
  Accepted request body format:<br>
  {<br>
    "title": "{title}",<br>
    "colorName": "{color name}"<br>
  }<br>
  <li>/{id} – POST – updates ToDoList entity by id for currently logged in user<br>
  Accepted request body format:<br>
  {<br>
    "title": "{title (optional)}",<br>
    "colorName": "{color name (optional)}"<br>
  }</li>
  <li>/{id} – DELETE – deleted ToDoList entity by id for currently logged in user</li>
</ul>
<b>ToDoItem (/api/v1/todoitems)</b>
<ul>
  <li>/all – GET – returns list of all uncompleted ToDoItem entities (sorted by ToDoList id) for currently logged in user</li>
  <li>/completed – GET – returns list of all COMPLETED ToDoItem entities (sorted by ToDoList id) for currently logged in user</li>
  <li>/all/{to-do-list-id} – GET – returns list of all uncompleted ToDoItem entities by to-do-list-id for currently logged in user</li>
  <li>/starred – GET – returns list of all starred and uncompleted ToDoItem entities for currently logged in user</li>
  <li>/{id} – GET – returns list of ToDoItem entity by id for currently logged in user</li>
  <li>/ – POST – creates ToDoItem entity for currently logged user<br>
  Accepted request body format:<br>
  {<br>
    "content": "{content}",<br>
    "toDoListId": {toDoListId},<br>
    "deadline": "{deadline (optional)}"<br>
  }</li>
  <li>/{id} – PATCH – updates ToDoItem entity by id for currently logged in user<br>
  Accepted request body format:<br>
  {<br>
    "content": "{content (optional)}",<br>
    "starred": {starred (optional)},<br>
    "completed": {completed (optional)},<br>
    "deadline": "{deadline (optional)}"
  }</li>
  <li>/{id} – DELETE – deletes ToDoItem entity by id for currently logged in user</li>
</ul>
