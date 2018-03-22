function parseurl() {
    let url = location.href;
    let param = url.split("?")[1];
    return param.split("=")[1];
}