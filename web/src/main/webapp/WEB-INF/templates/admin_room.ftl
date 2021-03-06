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
                       <span style="margin: 0 20px 0 0">Поиск заказа</span>
                       <form action="/library/search_employee" method="post">
                           <input type="search" name="search" placeholder="№ заказа">
                           <button class="searchButton">Найти</button>
                       </form>
                   </div>
               </div>
           </div>
       </div>
       <div class="row" style="height: 82%">
           <div class="col-lg" >
               <div class="list_container">
                   <h2>Список просроченных заказов</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Номер заказа</th>
                           <th>Номер билета</th>
                           <th>ФИО</th>

                           <th>Добавить в черный список</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list orderReaderList as orderReader>
                        <tr>

                            <td>
                                <p>${orderReader.orderNumber}</p>
                            </td>
                            <td>
                                <p>${orderReader.requestFromReader.reader.ticketNumber}</p>
                            </td>
                            <td>

                                <p>${orderReader.requestFromReader.reader.firstName} ${orderReader.requestFromReader.reader.lastName}</p>

                            </td>

                            <td>
                                <form action="/admin/room" method="post" >
                                    <button class="readernameButton" name="readerId" value="${orderReader.requestFromReader.reader.ticketNumber}" style="font-size: 18px">Добавить</button>
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
                <@pagination.pagination objectList=orderReaders pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "admin menu" />

