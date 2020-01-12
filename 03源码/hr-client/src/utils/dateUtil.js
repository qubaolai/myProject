export function getNowDate() {
  var date = new Date();
  var seperator1 = "-";
  var seperator2 = ":";
  var month = date.getMonth() + 1;
  var strDate = date.getDate();
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  var currentdate =
    date.getFullYear() +
    seperator1 +
    month +
    seperator1 +
    strDate +
    " " +
    date.getHours() +
    seperator2 +
    date.getMinutes() +
    seperator2 +
    date.getSeconds();
  return currentdate;
}
export function formatDate(date) {
  const year = date.getFullYear();
  const monthDate = date.getMonth() + 1;
  const month = monthDate < 10 ? "0" + monthDate : monthDate;
  const dayDate = date.getDate();
  const day = dayDate < 10 ? "0" + dayDate : dayDate;
  return year + "-" + month + "-" + day;
}
