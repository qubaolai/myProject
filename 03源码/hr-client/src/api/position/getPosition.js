import intercept from "@/utils/intercepter";

export function getpositions(data) {
  return intercept.request({
    method: "get",
    url: "/position/getPositionByDeptId",
    params: {
      deptId: data
    }
  });
}
