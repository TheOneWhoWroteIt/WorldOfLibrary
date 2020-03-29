<#include "macro_reader_room.ftl"/>

<#macro content_2>

    <#if requestOrder?? >

        <div class="col-lg-3">

            <div class="reader_profile_data">

                <#if orderReader??>
                    <h3>Информация о заказе</h3><br>
                    <p>
                        <b>Номер заказа:</b>№${orderReader.orderNumber}<br>
                        <b>Книги оформлены:</b> ${orderReader.startOrder}<br>
                        <b>Заказ необходимо закрыть до:</b> ${orderReader.validUntilDateOrder}<br>
                        <b>Читательский билет:</b> №${user.ticketNumber}

                    </p>
                <#elseif requestOrder??>
                    <h3>Информация о заявке</h3><br>
                    <p>
                        <b>Номер заявки:</b>№${requestOrder.id}<br>
                        <b>Заявка создана:</b> ${requestOrder.startRequest}<br>
                        <b>Заявка актуальна до:</b> ${requestOrder.validUntilDateRequest}<br>
                        <b>Читательский билет:</b> №${user.ticketNumber}

                    </p>
                </#if>


            </div>

        </div>

        <div class="col-lg-7">

            <div class="book_for_order">
                <#if requestOrder?? >
                    <#if orderReader??>

                        <h1>Текущий заказ</h1>
                    <#elseif requestOrder??>
                        <h1>Текущая заявка</h1>
                    </#if>
                </#if>

                <div class="row" style="height: 75%">

                    <div class="book_order_list">


                        <#list books as book>

                            <div class="col-lg">

                                <div class="book">
                                    <div class="row" style="height: 50%">
                                        <div class="col-lg" style="margin: 10px 0 0 0">
                                            <div class="book_cover_order">
                                                <img src="${book.imagePath}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row" style="height: 7%">
                                        <div class="col-lg" style="align-content: center; text-align: center">

                                            <form action="/reader/book_info" method="get">
                                                <button class="booknameButton" name="bookId" value="${book.id}"
                                                        style="font-size: 20px">${book.title}</button>

                                            </form>
                                        </div>
                                    </div>

                                    <div class="row" style="height: 7%">
                                        <div class="col-lg" style="align-content: center; text-align: center">
                                            <#list book.authors as author>

                                                <form action="/reader/author_info" method="get">
                                                    <button class="authornameButton" name="authorId"
                                                            value="${author.id}"
                                                            style="font-size: 20px">${author.firstName} ${author.lastName} </button>
                                                </form>
                                            </#list>
                                        </div>
                                    </div>


                                </div>
                            </div>

                        </#list>

                    </div>

                </div>

                <#if !orderReader??>
                <div class="row" style="height: 25%; width: 100%; text-align: center">
                    <div class="col-lg" >
                        <div class="order_button">
                            <form action="/reader/current_order" method="post">
                                <button name="readerId" value="${user.ticketNumber}" class="myButton">Отменить заявку</button>
                            </form>
                        </div>
                    </div>
                </div>
                </#if>

            </div>

        </div>

        <div class="col-lg-2">
            <div class="back_button">
                <form action="/reader/room">
                    <button class="myButton">Назад</button>
                </form>
            </div>

        </div>


    <#else>
        <div class="col-lg-2">
        </div>

        <div class="col-lg-8">


            <div class="book_for_order">
                <h1>Нет актуальной заявки/заказа</h1>
                <div class="row" style="height: 75%">
                </div>
                <div class="row" style="height: 25%">
                </div>
            </div>
        </div>

        <div class="col-lg-2">
            <div class="back_button">
                <form action="/reader/room">
                    <button class="myButton">Назад</button>
                </form>
            </div>
        </div>
    </#if>


</#macro>

<@reader_room_menu title="current order"/>