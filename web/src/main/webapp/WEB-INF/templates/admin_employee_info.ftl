<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_admin_menu.ftl" as ref>
    <@ref.admin_menu/>

</#macro>

<#macro content_3>

     <div class="employee_info_container">

        <div class="row" style="height: 85%">

            <div class="col-lg-5">
                <div class="book_info_cover" >
                    <img src="/resources/img/app_img/no foto.jpg" class="book_cover">
                </div>
            </div>

            <div class="col-lg-7">

                <div class="employee_data_info">

                    <h2>${user.firstName} ${user.lastName}</h2>
                    <p>
                        <b>Дата рождения:</b>${user.birthday}   <br>
                        <b>Email:</b> ${user.email}  <br>
                        <b>Номер телефона:</b> ${user.phoneNumber} <br>
                        <b>Адресс:</b> город - ${user.address.city}, улица - ${user.address.street},<br>
                                    номер дома - ${user.address.homeNumber}, номер квартиры - ${user.address.roomNumber}<br>
                        <b>Должность:</b> ${user.position} <br>
                        <b>Принят на работу:</b> ${user.startWork}  <br>
                        <#if user.endWork??>
                        <b>Уволен:</b>${user.endWork}   <br>
                        </#if>

                        <b>Login:</b> ${user.login}   <br>


                    </p>

                </div>

            </div>
        </div>

         <div class="row" style="height: 15%">

             <div class="col-lg">
                 <div class="edit_button_employee">
                     <form action="/admin/edit_employee" method="get">
                        <button class="addButton" name="userId" value="${user.personnelNumber}">Редактировать</button>
                     </form>
                 </div>
             </div>

         </div>
     </div>


</#macro>

<@emplouee_room_menu title = "employee info"/>