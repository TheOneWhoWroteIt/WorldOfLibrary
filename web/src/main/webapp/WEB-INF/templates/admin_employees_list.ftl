<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_admin_menu.ftl" as admin_ref>
    <@admin_ref.admin_menu/>

</#macro>

<#macro content_3>

   <div class="emplouee_various_lists">
       <div class="row" style="height: 10%">
           <div class="col-lg" >
               <div class="search_by_number">

                   <div class="search_container_emplouee">
                       <span style="margin: 0 20px 0 0">Поиск работника</span>
                       <form action="/library/search_employee" method="post">
                           <input type="search" name="search" placeholder="№ табеля">
                           <button class="searchButton">Найти</button>
                       </form>
                   </div>
               </div>
           </div>
       </div>
       <div class="row" style="height: 82%">
           <div class="col-lg" >
               <div class="list_container">
                   <h2>Список работников</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Табельный номер</th>
                           <th>ФИО</th>
                           <th>Начало работы</th>
                           <th>Дата увольнения</th>
                           <th>Редактировать данные</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list employeesList as employee>
                        <tr>

                            <td>
                                <p>${employee.personnelNumber}</p>
                            </td>
                            <td>

                                <form action="/admin/employee_info" method="get" >
                                    <button class="authornameButton" name="userId" value="${employee.personnelNumber}" style="font-size: 18px">${employee.firstName} ${employee.lastName}</button>
                                </form>
                            </td>
                            <td>
                                <p>${employee.startWork}</p>

                            </td>
                            <td>
                                <#if employee.endWork??>
                                <p>${employee.endWork}</p>
                                <#else > <p> - </p>
                                </#if>
                            </td>
                            <td>
                                <form action="/admin/edit_employee" method="get" >
                                    <button class="readernameButton" name="userId" value="${employee.personnelNumber}" style="font-size: 18px">Редактировать</button>
                                </form>
                            </td>


                        </tr>
                            </#list>

                       </tbody>
                   </table>
               </div>
           </div>
       </div>
       <div class="row" style="height: 8%">
           <div class="col-lg" >
              <#import "macro_pagination.ftl" as pagination>
                <@pagination.pagination objectList=employees pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "employee list"/>
