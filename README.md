Bell Integrator пример для учебного задания java.

Чтобы получить пользователя, офис, организацию, список гражданств или список удовстверений нужно отправить GET запрос.
Примеры обращения:
  http://localhost:8888/api/user/1
  http://localhost:8888/api/office/1
  http://localhost:8888/api/organization/1
  http://localhost:8888/api/countries
  http://localhost:8888/api/docs
  
Чтобы отфильтровать список, сохранить, обновить пользователя, офис или организацию нужно отправить POST запрос, указав в теле запроса необходимые параметры.

Примеры обращения:
  http://localhost:8888/api/user/save
  http://localhost:8888/api/office/update
  http://localhost:8888/api/organization/list
