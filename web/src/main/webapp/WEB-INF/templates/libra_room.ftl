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
                       <span style="margin: 0 20px 0 0">Поиск заявки по номеру</span>
                       <form action="/libra/search_request" method="post">
                           <input type="search" name="search" placeholder="№ заявки">
                           <button class="searchButton">Найти</button>
                       </form>
                   </div>
               </div>
           </div>
       </div>
       <div class="row" style="height: 82%">
           <div class="col-lg" >
               <div class="list_container">
                   <h2>Список заявок</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Номер заявки</th>
                           <th>Имя и Фамилия читателя</th>
                           <th>Дата подачи заявки</th>
                           <th>Срок истечения заявки</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list requestFromReadersList as readerRequest>
                        <tr>

                            <td>
                                <form action="/libra/request_from_reader" method="get" >
                                    <button class="authornameButton" name="requestId" value="${readerRequest.id}" style="font-size: 18px">${readerRequest.id}</button>
                                </form>
                            </td>
                            <td>
                                <form action="/libra/reader_info" method="post" >
                                    <button class="readernameButton" name="readerId" value="${readerRequest.reader.ticketNumber}" style="font-size: 18px">${readerRequest.reader.firstName} ${readerRequest.reader.lastName}</button>
                                </form>
                            </td>
                            <td>
                                <p>${readerRequest.startRequest}</p>
                            </td>
                            <td>
                                <p>${readerRequest.validUntilDateRequest}</p>
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
                <@pagination.pagination objectList=requestFromReaders pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "libra menu" />
