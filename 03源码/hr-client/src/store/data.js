const formData = {
  departments: [
    {
      value: "1",
      label: "部门1"
    },
    {
      value: "2",
      label: "部门2"
    },
    {
      value: "3",
      label: "部门3"
    },
    {
      value: "4",
      label: "部门4"
    },
    {
      value: "5",
      label: "部门5"
    }
  ],
  educations: [
    {
      value: "专科",
      label: "专科"
    },
    {
      value: "本科",
      label: "本科"
    },
    {
      value: "硕士",
      label: "硕士"
    },
    {
      value: "研究生",
      label: "研究生"
    }
  ],
  options: [
    {
      value: "1",
      label: "职称1"
    },
    {
      value: "2",
      label: "职称2"
    },
    {
      value: "3",
      label: "职称3"
    },
    {
      value: "4",
      label: "职称4"
    }
  ]
};
export function setData(data) {
  formData.departments = [];
  formData.options = [];
  const depts = [];
  const optionList = [];
  for (let i = 0; i < data.dept.length; i++) {
    const dept = {
      value: "",
      label: ""
    };
    dept.value = data.dept[i].id;
    dept.label = data.dept[i].name;
    depts.push(dept);
  }
  for (let i = 0; i < data.option.length; i++) {
    const option = {
      value: "",
      label: ""
    };
    option.value = data.option[i].id;
    option.label = data.option[i].name;
    optionList.push(option);
  }
  formData.departments = depts;
  formData.options = optionList;
}
export default {
  formData
};
