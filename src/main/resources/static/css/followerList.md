<!-- scssファイルでしたが、開発のため.mdにしてます -->

.list {
    display: flex;
    background-color: #FFCC33;
    border-radius: 5px;
    padding: 5px;
    margin: 5px;

    & button{
        width: 120px;
        height: 35px;
        background-color: #99FF33;
        right: 0px;
        border-radius: 10px;
        color: #fff;
        font-weight: bold;
        text-align: center;
        transform: translate(0, 100%);
    }

}
.list img{
    width: 8%;
    border-radius: 15px;
}

li {
    // background-color: #008037 !important;
    // color: white;
    // border-radius: 5px;
    // width: 650px;
    // height: 60px;
    // text-align: center;
    // font-weight: bold;
    // font-size:20px;
}


.user{
    background-color: #fff;
    width: 1040px;
    height: 100px !important;
    max-width: 1040px;
    border-radius: 5px;
    margin: 5px;
    font-size:15px;

    & #name{
        background-color: #CCFF99;
        font-weight: bold;
        font-size:20px;
    }
}

.follow-tab li{
    background-color: #008037 !important;
    color: white;
    border-radius: 5px;
    width: 650px;
    height: 60px;
    text-align: center;
    font-weight: bold;
    font-size:20px;
}