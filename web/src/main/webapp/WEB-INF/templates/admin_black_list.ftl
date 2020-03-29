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
                       <span style="margin: 0 20px 0 0">Поиск читателя</span>
                       <form action="/library/search_employee" method="post">
                           <input type="search" name="search" placeholder="№ билета">
                           <button class="searchButton">Найти</button>
                       </form>
                   </div>
               </div>
           </div>
       </div>
       <div class="row" style="height: 82%">
           <div class="col-lg" >
               <div class="list_container">
                   <h2>Черный список</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Номер билета</th>
                           <th>ФИО</th>

                           <th>Удалить из списка</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list blackListsContent as reader>
                        <tr>

                            <td>
                                <p>${reader.ticketNumber}</p>
                            </td>
                            <td>

                                <p>${reader.firstName} ${reader.lastName}</p>


                            </td>

                            <td>
                                <form action="/admin/black_list" method="post" <#--style="display:inline!important; margin: 0 0 0 50px"-->>
                                    <button class="readernameButton" name="readerId" value="${reader.ticketNumber}" style="font-size: 18px">Удалить</button>
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
           <div class="col-lg" <#--style="background: gold"-->>
               <#import "macro_pagination.ftl" as pagination>
                <@pagination.pagination objectList=blackLists pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "black list"/>
