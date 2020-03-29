<#include "macro_emplouee_room.ftl"/>

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
                       <span style="margin: 0 20px 0 0">Поиск читателя</span>
                       <form action="/libra/search_reader" method="post">
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
                   <h2>Список читателей</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Номер билета</th>
                           <th>ФИО</th>
                           <th>Номер заявки</th>
                           <th>Номер заказа</th>
                           <th>Черный список</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list readersList as reader>
                        <tr>

                            <td>
                                <p>${reader.ticketNumber}</p>
                            </td>
                            <td>

                                <form action="/libra/reader_info" method="post" >
                                    <button class="authornameButton" name="readerId" value="${reader.ticketNumber}" style="font-size: 18px">${reader.firstName} ${reader.lastName}</button>
                                </form>
                            </td>
                            <td>
                                <form action="/libra/request_from_reader" method="get" >

                                        <#if reader.requestFromReader?? && reader.requestFromReader.order??>
                                            <p> - </p>
                                        <#elseif reader.requestFromReader?? && !reader.requestFromReader.order??>
                                        <button class="readernameButton" name="requestId" value="${reader.requestFromReader.id}" style="font-size: 18px">
                                                ${reader.requestFromReader.id}
                                        </button>
                                        <#else><p> - </p>
                                        </#if>


                                </form>
                            </td>
                            <td>
                                <form action="/libra/order_info_and_close" method="get" >
                                    <#if reader.requestFromReader??>
                                        <#if reader.requestFromReader.order?? && reader.requestFromReader.order.orderExpired = false>
                                        <button class="readernameButton" name="orderId" value="${reader.requestFromReader.order.orderNumber}" style="font-size: 18px">
                                            ${reader.requestFromReader.order.orderNumber}
                                        </button>
                                        <#else><p> - </p>
                                        </#if>
                                    <#else><p> - </p>
                                    </#if>

                                </form>
                            </td>
                            <td>
                                    <#if reader.blackList == false>
                                    <p>НЕТ</p>
                                    <#else>
                                        <p>ДА</p>
                                    </#if>

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
                <@pagination.pagination objectList=readers pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "readers list" />
