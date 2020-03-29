<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_admin_menu.ftl" as ref>
    <@ref.admin_menu/>

</#macro>

<#macro content_3>

     <div class="add_employee_container">

         <h2>Регистрация нового работника</h2>

         <form action="/admin/employee_info" method="get" >

             <div class="info_by_employee">

                 <div class="info_enter">
                     <p>Имя</p>
                     <input type="text" name="firstName" placeholder="имя">
                 </div>

                 <div class="info_enter">
                     <p>Фамилия</p>
                     <input type="text" name="lastName" placeholder="фамилия">
                 </div>

                 <div class="info_enter">
                     <p>Дата рождения</p>
                     <input type="date" name="birthday" placeholder="">
                 </div>

                 <div class="info_enter">
                     <p>Email</p>
                     <input type="text" name="email" placeholder="email">
                 </div>

                 <div class="info_enter">
                     <p>Номер телефона</p>
                     <input type="text" name="phoneNumber" placeholder="+375(__) ___-__-__">
                 </div>

                 <div class="info_enter">
                     <p>Город</p>
                     <input type="text" name="city" placeholder="город">
                 </div>

                 <div class="info_enter">
                     <p>Улица</p>
                     <input type="text" name="street" placeholder="улица">
                 </div>

                 <div class="info_enter">
                     <p>Номер дома</p>
                     <input type="text" name="homeNumber" placeholder="№ дома">
                 </div>

                 <div class="info_enter">
                     <p>Номер квартиры</p>
                     <input type="text" name="roomNumber" placeholder="№ квартиры">
                 </div>

                 <div class="info_enter">
                     <p>Должность</p>
                     <input type="text" name="position" placeholder="должность">
                 </div>

                 <div class="info_enter">
                     <p>Дата трудоустройства</p>
                     <input type="date" name="startWork" placeholder="">
                 </div>

                 <div class="info_enter">
                     <p>Логин</p>
                     <input type="text" name="login" placeholder="логин">
                 </div>

                 <div class="info_enter">
                     <p>Пароль</p>
                     <input type="password" name="password" placeholder="**************">
                 </div>

                 <div class="info_enter">
                     <p>Уровень прав</p>
                     <p>
                         <select >

                             <option value="1">LIBRA</option>
                             <option value="2">ADMIN</option>

                         </select>
                     </p>
                 </div>

                 <div class="info_enter_file">
                     <div class="file_1">
                         <p>Загрузка фото</p>
                         <input type="file" name="f" >
                     </div>
                 </div>

                 <div class="add_button_employee">
                     <button class="addButton">Добавить</button>
                 </div>

             </div>

         </form>
     </div>


</#macro>

<@emplouee_room_menu title = "add employee"/>