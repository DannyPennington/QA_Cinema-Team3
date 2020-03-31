if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}
$("#modal").on("hidden.bs.modal", function (e) {
  $("#modal1 iframe").attr("src", $("#modal1 iframe").attr("src"));
});
$("#modal2").on("hidden.bs.modal", function (e) {
  $("#modal2 iframe").attr("src", $("#modal2 iframe").attr("src"));
});
$("#modal3").on("hidden.bs.modal", function (e) {
  $("#modal3 iframe").attr("src", $("#modal3 iframe").attr("src"));
});
$("#modal4").on("hidden.bs.modal", function (e) {
  $("#modal4 iframe").attr("src", $("#modal4 iframe").attr("src"));
});

