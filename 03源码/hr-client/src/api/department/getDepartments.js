import intercept from "@/utils/intercepter";

export function getDeptList(data) {
  return intercept.request({
    method: "post",
    url: "/department/departmentList",
    data: data
  });
}
