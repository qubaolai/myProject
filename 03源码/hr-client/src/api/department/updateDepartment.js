import intercept from "@/utils/intercepter";

export function updateDept(data) {
  return intercept.request({
    method: "put",
    url: "/department/updateDepartment",
    data: data
  });
}
