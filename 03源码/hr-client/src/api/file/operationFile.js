import intercept from "@/utils/intercepter";

//获取用户头像路径
export function getPhoto() {
  return intercept.request({
    method: "get",
    url: "/file/getHeadPhoto"
  });
}
export function uploadFile(data) {
  return intercept.request({
    method: "post",
    url: "/file/uploadFile",
    data: data
  });
}
export function deleteFile(data) {
  return intercept.request({
    method: "delete",
    url: "/file/deleteFile",
    params: { path: data }
  });
}
export function insertFileInfo(data) {
  return intercept.request({
    method: "get",
    url: "/file/addFileInfo",
    params: { path: data }
  });
}
