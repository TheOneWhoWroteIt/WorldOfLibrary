<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_ref>
    <@libra_ref.libra_menu/>

</#macro>

<#macro content_3>
<form action="/libra/order_info_and_close" method="post" >

    <div class="request_from_reader_container">
        <div class="row" style="height: 15%/*; background: red*/">
            <div class="col-lg">
                <div class="info_by_request">
                    <h2>Заказ №${orderReader.orderNumber}</h2>
                    <div class="by_reader" >
                        <p>${orderReader.requestFromReader.reader.firstName} ${orderReader.requestFromReader.reader.lastName}</p>
                        <p>читательский билет №${orderReader.requestFromReader.reader.ticketNumber}</p>
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
                            <th>Сдано</th>

                        </tr>
                        </thead>
                        <tbody>

                            <#list orderReader.requestFromReader.books as book>
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
                    <h3>Закрыть заказ</h3>



                    <div class="close_order_date" style="display: inline-flex">
                    <div class="info_date_order">
                            <p>Дата открытия</p>
                            <p>${orderReader.startOrder}</p>

                        </div>

                        <div class="info_date_order">
                            <p>Действует до</p>
                            <p>${orderReader.validUntilDateOrder}</p>

                        </div>

                        <div class="info_enter">
                            <p>Дата закрытия</p>
                            <input type="date" name="endDate" placeholder="дата рождения">
                        </div>

                        <div class="open_order_button">
                            <button class="regButton" name="orderId" value="${orderReader.orderNumber}">Закрыть</button>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</form>

</#macro>

<@emplouee_room_menu title = "order info" />