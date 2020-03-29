<#macro reader_room_menu title>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap&subset=cyrillic" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Marck+Script&display=swap&subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/css/button.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/reader_room_menu.css">
    <link rel="icon" type="images/png" href="/resources/img/app_img/favicon.png">
    <title>${title}</title>
</head>
<body>

<div class="reader_one_container" >

    <div class="container-fluid">
        <div class="row" style="height: 10%" >


            <div class="col-lg-10 ">
                <div class="search_container">

                    <form action="/reader/find_by_title_or_name"  method="get" enctype="multipart/form-data">

                        <input class="search_input"  type="radio" name="searchradio" value="authorSearch" ><span>Искать по автору</span>

                        <input class="search_input"  type="radio" name="searchradio" value="bookSearch" checked><span>Искать по названию книги</span>

                        <input class="search_enter" type="search" name="search"  placeholder="Поиск...">

                        <button class="searchButton">Найти</button>

                    </form>
                </div>

            </div>


            <div class="col-lg-2">
                <div class="out_button">
                    <form action="/logout" method="post">
                        <button class="outButton">Выйти</button>
                    </form>
                </div>
            </div>

        </div>

        <div class="row " style="height: 80%" >

            <@content_2/>

        </div>

        <div class="row " style="height: 10%" >

            <div class="col-lg ">
                <div class="search_alfavit">
                    <form action="/reader/find_by_letter" method="get" enctype="multipart/form-data">
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="А">А</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Б">Б</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="В">В</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Г">Г</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Д">Д</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Е">Е</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Ж">Ж</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="З">З</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="И">И</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="К">К</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Л">Л</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="М">М</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Н">Н</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="О">О</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="П">П</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Р">Р</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="С">С</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Т">Т</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="У">У</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Ф">Ф</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Х">Х</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Ц">Ц</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Ч">Ч</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Ш">Ш</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Щ">Щ</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Э">Э</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Ю">Ю</button></div>
                        <div class="alfavit"><button class="alfavitButton" name="letter" value="Я">Я</button></div>
                    </form>
                </div>
            </div>


        </div>

    </div>

</div>

</body>
</html>

</#macro>