<#include "macro_emplouee_room.ftl" />


<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_ref>
    <@libra_ref.libra_menu/>

</#macro>

<#macro content_3>

   <div class="emplouee_various_lists">
       <div class="row" style="height: 10%">
           <div class="col-lg" >
               <div class="search_by_number">

                   <div class="search_container_emplouee">
                       <span style="margin: 0 20px 0 0">Поиск заказа по номеру</span>
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
                   <h2>Список заказов</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Номер заказа</th>
                           <th>ФИО</th>
                           <th>Дата открытия заказа</th>
                           <th>Срок истечения заказа</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list ordersList as orderReader>
                        <tr>

                            <td>
                                <form action="/libra/order_info_and_close" method="get" >
                                    <button class="authornameButton" name="orderId" value="${orderReader.orderNumber}" style="font-size: 18px">${orderReader.orderNumber}</button>
                                </form>
                            </td>
                            <td>
                                <form action="/libra/reader_info" method="post" >
                                    <button class="readernameButton" name="readerId" value="${orderReader.requestFromReader.reader.ticketNumber}" style="font-size: 18px">${orderReader.requestFromReader.reader.firstName} ${orderReader.requestFromReader.reader.lastName}</button>
                                </form>
                            </td>
                            <td>
                                <p>${orderReader.startOrder}</p>
                            </td>
                            <td>
                                <p>${orderReader.validUntilDateOrder}</p>
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
                <@pagination.pagination objectList=orders pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "open orders" />


