function regMail(mail) {
    let reg = new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$");
    return reg.test(mail);
}

function regPhone(phone) {
    let reg = new RegExp("^[1][3|5|8]\\d{9}$");
    return reg.test(phone);
}