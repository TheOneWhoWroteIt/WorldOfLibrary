<#include "macro_reader_room.ftl"/>

<#macro content_2>

    <div class="col-lg-3">

        <h3>Поиск по жанрам</h3>

        <div class="genre">

            <form action="/reader/find_by_genre"  method="get" >
                <button class="genrenameButton" name="genreName" value="Детектив"> Детектив</button>
                <button class="genrenameButton" name="genreName" value="Исторический роман"> Исторический роман</button>
                <button class="genrenameButton" name="genreName" value="Любовный роман"> Любовный роман</button>
                <button class="genrenameButton" name="genreName" value="Мистика"> Мистика</button>
                <button class="genrenameButton" name="genreName" value="Приключения"> Приключения</button>
                <button class="genrenameButton" name="genreName" value="Триллер"> Триллер</button>
                <button class="genrenameButton" name="genreName" value="Научная фантастика"> Научная фантастика</button>
                <button class="genrenameButton" name="genreName" value="Контркультура"> Контркультура</button>
                <button class="genrenameButton" name="genreName" value="Фэнтези"> Фэнтези</button>
                <button class="genrenameButton" name="genreName" value="Музыка"> Музыка</button>
                <button class="genrenameButton" name="genreName" value="Обучающая литература"> Обучающая литература</button>
                <button class="genrenameButton" name="genreName" value="Публицистика"> Публицистика</button>
                <button class="genrenameButton" name="genreName" value="Научная литература"> Научная литература</button><br>
                <button class="genrenameButton" name="genreName" value="ALL" style="font-weight: bold"> Все жанры</button>
            </form>
        </div>

    </div>

    <div class="col-lg-6">

        <div class="row" style="height: 90%">
            <div class="col-lg">

                <div class="book_container">

                    <#list booksList as book>

                    <div class="book" >

                            <div class="row" style="height: 80%">
                                <div class="col-lg" style="margin: 10px 0 0 0">
                                    <div class="img_cover">
                                        <img  src="${book.imagePath}"  />
                                    </div>
                                </div>
                            </div>

                            <div class="row" style="height: 10%">
                                <div class="col-lg" style="align-content: center; text-align: center">
                                    <form action="/reader/book_info" method="get" >
                                        <button class="booknameButton" name="bookId" value="${book.id}">${book.title}</button>

                                    </form>
                                </div>
                            </div>

                            <div class="row" style="height: 10%">
                                <div class="col-lg" style="align-content: center; text-align: center">
                                    <#list book.authors as author>
                                    <form action="/reader/author_info" method="get" >
                                        <button class="authornameButton" name="authorId" value="${author.id}" >${author.firstName} ${author.lastName} </button>
                                    </form>
                                    </#list>
                                </div>
                            </div>



                    </div>


                    </#list>
                </div>

            </div>
        </div>

        <div class="row" style="height: 10%">
            <div class="col-lg">

                <#import "macro_pagination.ftl" as pagination>
                <@pagination.pagination objectList=books pageNumber=numberPage url=pageUrl/>

            </div>
        </div>

    </div>

    <div class="col-lg-3" >

        <div class="welcome_text">
            <div class="hello_text">

                Добрый день, ${user.firstName}!<br>
                Читательский билет: №${user.ticketNumber}<br>
                Город: ${user.address.city}<br>
                Email: ${user.email}

            </div>

            <div class="edit_profil">
                <form action="/reader/edit_profile" method="get">
                    <button class="editprofilButton" name="readerId" value="${user.ticketNumber}">Редактировать профиль</button>
                </form>
            </div>

            <#if !user.requestFromReader??>
            <div class="create_order">
                    <form action="/reader/create_order" method="get">
                        <button class="createorderButton">Оформить заявку</button>
                    </form>
                </div>
            <#else >
            </#if>

            <div class="current_order">
                <form action="/reader/current_order" method="get">
                    <button class="createorderButton" name = "readerId" value="${user.ticketNumber}">Текущий заказ</button>
                </form>
            </div>


        </div>

    </div>


</#macro>

<@reader_room_menu title = "reader room"/>