<#macro info title>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap&subset=cyrillic" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Neucha&display=swap&subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/css/info.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/button.css">
    <link rel="icon" type="images/png" href="/resources/img/app_img/favicon.png">
    <title>${title}</title>
</head>
<body>

<div class="about_one_container" >

    <div class="container_info_text">
        <div class="container-fluid">
            <div class="row justify-content-between" style="height: 90%" >

                <div class="col-lg-4">
                </div>


                <div class="col-lg-8 ">

                    <div class="text_container">

                    <@content/>

                    </div>

                </div>

            </div>

            <div class="row justify-content-between" style="height: 10%">
                <div class="col-lg-10">
                </div>
                <div class="col-lg-2">

                    <div class="back_button">
                        <form action="/welcome">
                            <button class="myButton">Назад</button>
                        </form>
                    </div>

                </div>
            </div>

        </div>
    </div>


</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



</body>
</html>

</#macro>