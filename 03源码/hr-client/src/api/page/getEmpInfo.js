import intercept from "@/utils/intercepter";

export function getEmpInfo() {
  return intercept.request({
    method: "get",
    url: "/initPage/getPageEmpInfo"
  });
}
