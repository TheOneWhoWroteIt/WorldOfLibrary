<#include "macro_reader_room.ftl"/>

<#macro content_2>
    <#if booksRequest??>

    <div class="col-lg-2">
    </div>

    <div class="col-lg-8">

        <div class="book_for_order">

            <h1>Выбранные книги</h1>

            <div class="row" style="height: 75%">

                <div class="book_order_list">

    <#list booksRequest as book>

            <div class="col-lg<#---2-->">

                <div class="book" >
                    <div class="row" style="height: 50%">
                        <div class="col-lg" style="margin: 10px 0 0 0">
                            <div class="book_cover_order">
                            <img src="${book.imagePath}"  >
                            </div>
                        </div>
                    </div>

                    <div class="row" style="height: 7%">
                        <div class="col-lg" style="align-content: center; text-align: center">
                            <form action="/reader/book_info" method="get" >
                                <button class="booknameButton" name="bookId" value="${book.id}" style="font-size: 20px">${book.title}</button>

                            </form>
                        </div>
                    </div>

                    <div class="row" style="height: 7%">
                        <div class="col-lg" style="align-content: center; text-align: center">
                            <#list book.authors as author>
                            <form action="/reader/author_info" method="get" >
                                <button class="authornameButton" name="authorId" value="${author.id}" style="font-size: 20px">${author.firstName} ${author.lastName} </button>
                            </form>
                            </#list>
                        </div>
                    </div>

                    <div class="row" style="height: 7%">
                        <div class="col-lg" style="align-content: center; text-align: center">
                            <form action="/reader/book_delete" method="post" >
                                <button class="authornameButton" name="bookId" value="${book.id}" style="font-size: 20px">Удалить </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            </#list>

            </div>

        </div>

            <div class="row" style="height: 25%; width: 100%; text-align: center">
                <div class="col-lg" >
                    <div class="order_button">
                        <form action="/reader/create_order" method="post">
                            <button class="orderButton">Оформить</button>
                        </form>
                    </div>
                </div>
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


    <#else >
    <div class="col-lg-2">
    </div>

    <div class="col-lg-8">


        <div class="book_for_order">
        <h1>Нет выбранных книг</h1>
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

<@reader_room_menu title="create order"/>