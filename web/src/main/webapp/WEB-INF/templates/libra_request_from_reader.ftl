<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_ref>
    <@libra_ref.libra_menu/>

</#macro>

<#macro content_3>

<form action="/libra/request_from_reader" method="post">

    <div class="request_from_reader_container">
        <div class="row" style="height: 15%/*; background: red*/">
            <div class="col-lg">
                <div class="info_by_request">
                    <h2>Заявка №${requestFromReader.id}</h2>
                    <div class="by_reader" >
                        <form action="/libra/reader_info" method="get" >
                            <button class="readernameButton" style="font-size: 24px" name="readerId" value="">${requestFromReader.reader.firstName} ${requestFromReader.reader.lastName}</button>
                        </form>
                        <p>читательский билет №${requestFromReader.reader.ticketNumber}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="height: 60%/*; background: aqua*/">
            <div class="col-lg">

                   <div class="list_container">
                       <h2>Список книг</h2>
                       <table class="table">
                           <thead style="font-size: 22px">
                           <tr>
                               <th>Название книги</th>
                               <th>Имя и Фамилия автора</th>
                               <th>Добавить в заказ</th>
                               <th>Кол-во в каталоге</th>

                           </tr>
                           </thead>
                           <tbody>

                            <#list requestFromReader.books as book>
                        <tr>

                            <td>
                                <p>${book.title}</p>

                            </td>
                            <td>
                                <#list book.authors as author>
                                    <p>${author.firstName} ${author.lastName}</p>
                                </#list>

                            </td>
                            <td>
                                <label><input name="check" value="${book.id}" checked type="checkbox" style="font-size: 28px" /> </label>
                            </td>
                            <td>
                                <p>${book.printEditionCount}</p>
                            </td>


                        </tr>
                            </#list>


                           </tbody>
                       </table>
                   </div>

            </div>
        </div>

        <div class="row" style="height: 25%/*; background: gold*/">
            <div class="col-lg">
                <div class="action_for_order">
                    <h3>Открыть заказ</h3>


                    <div style="display: inline-flex">

                        <div class="info_enter">
                            <p>Дата открытия</p>
                            <input type="date" name="startDate" placeholder="">
                        </div>

                        <div class="info_enter">
                            <p>Действует до</p>
                            <input type="date" name="validDate" placeholder="">
                        </div>

                        <div class="open_order_button">
                            <button class="regButton" name="requestId" value="${requestFromReader.id}">Открыть</button>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>

</form>
</#macro>

<@emplouee_room_menu title = "request reader" />