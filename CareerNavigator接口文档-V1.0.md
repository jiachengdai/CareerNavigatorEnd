# CareerNavigator接口文档-V1.0

## 账户（Account）相关接口

### 注册

#### 基本信息

> 请求路径：`/account/register`
> 请求方式：`POST`
> 接口描述：该接口用于注册新用户。

#### 请求参数

请求参数格式：`application/x-www-form-urlencoded`

| 参数名称 | 说明     | 类型    | 是否必须 | 备注                                   |
| -------- | -------- | ------- | -------- | -------------------------------------- |
| username | 用户名   | string  | 是       | 5~16 位非空字符                        |
| password | 密码     | string  | 是       | 5~16 位非空字符                        |
| type     | 用户类型 | integer | 是       | 类型，1 - 普通用户，2 - 管理员（暂定） |

请求数据样例：

```
username=john_doe&password=123456&type=1
```

#### 响应数据

响应数据类型：`application/json`

| 名称    | 类型   | 是否必须 | 默认值 | 备注                   |
| ------- | ------ | -------- | ------ | ---------------------- |
| code    | number | 是       |        | 响应码，0-成功，1-失败 |
| message | string | 否       |        | 提示信息               |
| data    | object | 否       |        | 返回的数据（null）     |

响应数据样例：

```
{
  "code": 0,
  "message": "操作成功",
  "data": null
}
```

------

### 用户登录

#### 基本信息

> 请求路径：`/account/login`
> 请求方式：`POST`
> 接口描述：该接口用于用户登录，成功后返回 JWT 令牌。

#### 请求参数

请求参数格式：`application/x-www-form-urlencoded`

| 参数名称 | 说明   | 类型   | 是否必须 | 备注            |
| -------- | ------ | ------ | -------- | --------------- |
| username | 用户名 | string | 是       | 5~16 位非空字符 |
| password | 密码   | string | 是       | 5~16 位非空字符 |

请求数据样例：

```
username=john_doe&password=123456
```

#### 响应数据

响应数据类型：`application/json`

| 名称    | 类型   | 是否必须 | 默认值 | 备注                   |
| ------- | ------ | -------- | ------ | ---------------------- |
| code    | number | 是       |        | 响应码，0-成功，1-失败 |
| message | string | 否       |        | 提示信息               |
| data    | string | 是       |        | 返回的 JWT 令牌        |

响应数据样例：

```
{
  "code": 0,
  "message": "操作成功",
  "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### 备注说明

> 登录成功后会自动返回 JWT 令牌，在后续请求中需通过请求头 `Authorization: Bearer {token}` 携带令牌。
> 如果未携带或令牌无效，将返回 HTTP 状态码 **401 Unauthorized**。

------

### 获取用户信息

#### 基本信息

> 请求路径：`/account/info`
> 请求方式：`GET`
> 接口描述：获取指定用户名的用户账户信息（不包含密码）。

#### 请求参数

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| username | 用户名 | string | 是       | 用户名唯一标识 |

请求数据样例：

```
username=lili
```

#### 响应数据

响应数据类型：`application/json`

| 名称    | 类型   | 是否必须 | 备注                   |
| ------- | ------ | -------- | ---------------------- |
| code    | number | 是       | 响应码，0-成功，1-失败 |
| message | string | 否       | 提示信息               |
| data    | object | 是       | 用户对象（无密码）     |

响应数据样例：

```
{
    "code": 0,
    "message": "操作成功",
    "data": {
        "username": "lili",
        "password": null,
        "avatarUrl": null,
        "type": 1
    }
}
```

------

### 4. 修改密码

#### 基本信息

> 请求路径：`/account/changepassword`
> 请求方式：`POST`
> 接口描述：该接口用于修改密码。

#### 请求参数

请求参数格式：`application/x-www-form-urlencoded`

| 参数名称    | 说明   | 类型   | 是否必须 | 备注             |
| ----------- | ------ | ------ | -------- | ---------------- |
| username    | 用户名 | string | 是       | 当前用户名       |
| oldPassword | 原密码 | string | 是       | 当前密码（明文） |
| newPassword | 新密码 | string | 是       | 新密码（明文）   |

请求数据样例：

```
username=john_doe&oldPassword=123456&newPassword=abcdef
```

#### 响应数据

响应数据类型：`application/json`

响应参数与注册接口相同。

响应数据样例：

```
{
  "code": 0,
  "message": "修改成功",
  "data": null
}
```

------

### 5. 修改用户名

#### 基本信息

> 请求路径：`/account/updateUsername`
> 请求方式：`POST`
> 接口描述：修改用户名，需验证密码。

#### 请求参数

请求参数格式：`application/x-www-form-urlencoded`

| 参数名称    | 说明     | 类型   | 是否必须 | 备注             |
| ----------- | -------- | ------ | -------- | ---------------- |
| oldUsername | 原用户名 | string | 是       | 当前账户名       |
| newUsername | 新用户名 | string | 是       | 要修改的新账户名 |
| password    | 当前密码 | string | 是       | 用于验证身份     |

请求数据样例：

```
oldUsername=john_doe&newUsername=john_lee&password=123456
```

#### 响应数据

响应数据类型：`application/json`

```
{
  "code": 0,
  "message": "操作成功",
  "data": null
}
```

------

### 6. 用户登出

#### 基本信息

> 请求路径：`/account/logout`
> 请求方式：`POST`
> 接口描述：注销登录，清除缓存中的 JWT。

#### 请求参数

请求头携带：

| 参数名称      | 说明    | 类型   | 是否必须 | 备注                       |
| ------------- | ------- | ------ | -------- | -------------------------- |
| Authorization | JWT令牌 | string | 是       | 格式：Bearer + 空格 + 令牌 |

请求数据样例：

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### 响应数据

响应数据类型：`application/json`

```
{
  "code": 0,
  "message": "操作成功",
  "data": null
}
```

## 用户（User）相关接口

###  添加用户

#### 基本信息

> 请求路径：`/user/add`
> 请求方式：`POST`
> 接口描述：添加一个新用户。

#### 请求参数

请求格式：`application/json`

| 参数名称       | 类型    | 是否必须 | 说明     |
| -------------- | ------- | -------- | -------- |
| username       | string  | 是       | 用户名   |
| nickname       | string  | 否       | 昵称     |
| sex            | string  | 否       | 性别     |
| age            | integer | 否       | 年龄     |
| tel            | string  | 否       | 电话     |
| email          | string  | 否       | 邮箱     |
| education      | string  | 否       | 教育背景 |
| major          | string  | 否       | 专业     |
| expectedJob    | string  | 否       | 期望职位 |
| graduationTime | string  | 否       | 毕业时间 |

请求示例：

```
{
  "username": "john_doe",
  "nickname": "小约翰",
  "sex": "男",
  "age": 23,
  "tel": "12345678901",
  "email": "john@example.com",
  "education": "本科",
  "major": "软件工程",
  "expectedJob": "Java后端开发",
  "graduationTime": "2025-06"
}
```

#### 响应数据

```
{
  "code": 0,
  "message": "添加成功",
  "data": null
}
```

#### 备注

`username` 字段必须与 `account` 表中的 `username` 字段一致，否则会导致外键约束错误。

###  根据 ID 查询用户

#### 基本信息

> 请求路径：`/user/{id}`
> 请求方式：`GET`
> 接口描述：根据用户 ID 查询用户信息。

#### 请求参数

| 参数名称 | 说明   | 类型    | 是否必须 | 备注     |
| -------- | ------ | ------- | -------- | -------- |
| id       | 用户ID | integer | 是       | 用户主键 |

请求示例：

```
GET /user/1
```

#### 响应数据

```
{
  "code": 0,
  "message": "查询成功",
  "data": {
    "id": 1,
    "username": "john_doe",
    "nickname": "小约翰",
    "sex": "男",
    "age": 23,
    "tel": "12345678901",
    "email": "john@example.com",
    "education": "本科",
    "major": "计算机科学",
    "expectedJob": "Java后端开发工程师",
    "graduationTime": "2025-06"
  }
}
```

------

###  根据用户名查询用户

#### 基本信息

> 请求路径：`/user/username/{username}`
> 请求方式：`GET`
> 接口描述：根据用户名查询用户信息。

#### 请求参数

| 参数名称 | 说明   | 类型   | 是否必须 |
| -------- | ------ | ------ | -------- |
| username | 用户名 | string | 是       |

请求示例：

```
GET /user/username/john_doe
```

#### 响应数据

同上。

------

### 查询用户列表（分页查询）

#### 基本信息

> 请求路径：`/user/list`
> 请求方式：`GET`
> 接口描述：查询系统中所有用户的列表，支持分页查询。

#### 请求参数

| 参数名称 | 说明     | 类型    | 是否必须 | 备注        |
| -------- | -------- | ------- | -------- | ----------- |
| pageNum  | 页码     | integer | 否       | 默认值为 1  |
| pageSize | 每页大小 | integer | 否       | 默认值为 10 |

请求示例：

1. *分页查询示例**：

```
GET /user/list?pageNum=1&pageSize=10
```

  2.*无分页查询示例**：

```
GET /user/list
```

#### 响应数据

```
{
    "code": 0,
    "message": "操作成功",
    "data": {
        "total": 4,
        "list": [
            {
                "id": 2,
                "username": "小明",
                "nickname": null,
                "sex": null,
                "age": null,
                "education": null,
                "major": null,
                "tel": null,
                "email": null,
                "expectedjob": null,
                "graduationtime": null,
                "registertime": "2023-03-01"
            },
            {
                "id": 3,
                "username": "lrj",
                "nickname": "daria",
                "sex": "女",
                "age": 21,
                "education": "本科",
                "major": "软件工程",
                "tel": "12345678900",
                "email": "jinli@example.com",
                "expectedjob": "Java开发工程师",
                "graduationtime": "2026-06-015",
                "registertime": "2025-04-01"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2
    }
}
```

#### 备注

- 如果请求时未传递 `pageNum` 和 `pageSize`，则默认为无分页查询，返回所有用户数据。
- `pageNum` 和 `pageSize` 的最大值会根据数据库中的数据量自动调整，超出范围时将返回空数据。
- 返回的分页信息包括：`total`（总条数）、`pages`（总页数）、`pageNum`（当前页码）和 `pageSize`（每页大小）。

------

### 更新用户

#### 基本信息

> 请求路径：`/user/update`
> 请求方式：`PUT`
> 接口描述：更新用户的所有信息字段。

#### 请求参数

请求格式：`application/json`

结构同添加用户，需包含 `id` 字段：

请求示例：

```
{
  "id": 1,
  "username": "john_doe",
  "nickname": "新昵称",
  "sex": "男",
  "age": 24,
  "tel": "18812345678",
  "email": "john@updated.com",
  "education": "硕士",
  "major": "人工智能",
  "expectedJob": "AI工程师",
  "graduationTime": "2026-07"
}
```

#### 响应数据

```
{
  "code": 0,
  "message": "更新成功",
  "data": {
    "id": 1,
    "username": "john_doe",
    "nickname": "新昵称",
    "sex": "男",
    "age": 24,
    "tel": "18812345678",
    "email": "john@updated.com",
    "education": "硕士",
    "major": "人工智能",
    "expectedJob": "AI工程师",
    "graduationTime": "2026-07"
  }
}
```

------

### 6. 删除用户

#### 基本信息

> 请求路径：`/user/{id}`
> 请求方式：`DELETE`
> 接口描述：根据 ID 删除用户。

#### 请求参数

| 参数名称 | 类型    | 是否必须 | 说明    |
| -------- | ------- | -------- | ------- |
| id       | integer | 是       | 用户 ID |

请求示例：

```
DELETE /user/1
```

#### 响应数据

```
{
  "code": 0,
  "message": "删除成功",
  "data": null
}
```

## 用户画像（UserProfile）相关接口

### 获取用户画像数据

#### **基本信息**

> 请求路径：`/userprofile/{username}`
>
> 请求方式：`GET`
>
> 接口描述：该接口用于获取指定用户名的用户画像数据。

#### **请求参数**

| 参数名称 | 说明   | 类型   | 是否必须 | 备注                     |
| -------- | ------ | ------ | -------- | ------------------------ |
| username | 用户名 | string | 是       | 需要获取用户画像的用户名 |

**请求数据样例：**

```
GET /userprofile/lrj
```

#### **响应数据**

```
{
    "projectList": [
        {
            "projectid": 2,
            "id": null,
            "projectName": "在线教育平台",
            "role": "后端开发工程师",
            "startDate": "2023-01-01",
            "endDate": "2023-06-30",
            "description": "使用Spring Boot开发后端API",
            "briefIntroduction": "一个在线教育平台的后端开发项目",
            "resumeid": 1
        },
        {
            "projectid": 3,
            "id": null,
            "projectName": "智能推荐系统",
            "role": "算法工程师",
            "startDate": "2023-07-01",
            "endDate": "2023-12-31",
            "description": "基于协同过滤算法实现个性化推荐",
            "briefIntroduction": "一个智能推荐系统的开发项目",
            "resumeid": 1
        }
    ],
    "summary": "lrj，25岁，测试大学毕业，专业：计算机科学与技术。教育背景：测试大学(本科)、测试大学(硕士)。项目经验：在线教育平台(后端开发工程师)、智能推荐系统(算法工程师)。",
    "personalityTraits": {
        "责任心": 0.07585818002124355,
        "创新能力": 0.07585818002124355,
        "沟通能力": 0.07585818002124355,
        "团队协作": 0.07585818002124355,
        "领导力": 0.07585818002124355
    },
    "industryTrends": {
        "cloud": 0.0,
        "bigdata": 0.0,
        "blockchain": 0.0,
        "ai": 0.0,
        "iot": 0.0
    },
    "personalInfo": {
        "id": 2,
        "name": "lrj",
        "gender": "男",
        "phone": "13800138000",
        "email": "testuser@example.com",
        "university": "测试大学",
        "politicalstatus": "群众",
        "website": null,
        "avatar": null,
        "major": "计算机科学与技术",
        "age": 25,
        "applicationPosition": "Java开发工程师",
        "resumeid": 1
    },
    "careerInterests": {
        "后端": 0.5
    },
    "keywords": [
        "算法工程师",
        "Boot开发后端API",
        "本科",
        "软件工程",
        "编程竞赛一等奖",
        "使用Spring",
        "后端开发工程师",
        "校级优秀毕业生",
        "Java开发工程师",
        "测试大学",
        "全国大学生编程竞赛一等奖",
        "硕士",
        "在线教育平台",
        "智能推荐系统",
        "基于协同过滤算法实现个性化推荐",
        "优秀毕业生",
        "计算机科学与技术"
    ],
    "educationList": [
        {
            "eduid": 2,
            "id": null,
            "school": "测试大学",
            "degree": "本科",
            "major": "计算机科学与技术",
            "startDate": "2020-09-01",
            "endDate": "2024-06-30",
            "resumeid": 1
        },
        {
            "eduid": 3,
            "id": null,
            "school": "测试大学",
            "degree": "硕士",
            "major": "软件工程",
            "startDate": "2024-09-01",
            "endDate": "2026-06-30",
            "resumeid": 1
        }
    ],
    "competency": {
        "spring": 0.5,
        "算法": 0.5
    },
    "skillGaps": {
        "后端开发工程师": [
            "java",
            "python",
            "database",
            "api"
        ]
    },
    "marketValue": {
        "初级": 0.35,
        "中级": 0.5599999999999999,
        "高级": 0.7
    },
    "learningPath": {
        "后端开发工程师": [
            "学习：java",
            "学习：python",
            "学习：database",
            "学习：api"
        ]
    },
    "salaryExpectation": {
        "初级": 12500.0,
        "中级": 14000.0,
        "高级": 13500.0
    },
    "recommendations": [
        "后端开发工程师"
    ],
    "careerGoals": {
        "后端": [
            "短期目标：掌握后端基础知识",
            "中期目标：参与后端相关项目",
            "长期目标：成为后端领域专家"
        ]
    },
    "workLifeBalance": {
        "工作强度": 1.0,
        "生活平衡": 1.0
    },
    "developmentSuggestions": [
        "建议提升后端开发工程师方向所需的技能：java、python、database、api"
    ],
    "skillsList": [
        {
            "skillid": 1,
            "id": null,
            "skillName": "Java",
            "resumeid": 1
        },
        {
            "skillid": 2,
            "id": null,
            "skillName": "Spring Boot",
            "resumeid": 1
        },
        {
            "skillid": 3,
            "id": null,
            "skillName": "MySQL",
            "resumeid": 1
        },
        {
            "skillid": 4,
            "id": null,
            "skillName": "Python",
            "resumeid": 1
        },
        {
            "skillid": 5,
            "id": null,
            "skillName": "机器学习",
            "resumeid": 1
        }
    ],
    "honorList": [
        {
            "honorid": 2,
            "id": null,
            "honorName": "优秀毕业生",
            "date": "2024-06-30",
            "description": "校级优秀毕业生",
            "resumeid": 1
        },
        {
            "honorid": 3,
            "id": null,
            "honorName": "编程竞赛一等奖",
            "date": "2023-12-15",
            "description": "全国大学生编程竞赛一等奖",
            "resumeid": 1
        }
    ],
    "networkingSuggestions": {
        "后端": [
            "参加后端技术社区活动",
            "关注后端领域的技术博客"
        ]
    }
}

```
## MBTI 测评（Mbti）相关接口

### 获取测试题目

#### 基本信息

> 请求路径：`/mbti/questions`
> 请求方式：`GET`
> 接口描述：获取所有可用的 MBTI 测试题目。

#### 请求参数

无

请求示例：

```
GET /mbti/questions
```

#### 响应数据

```
{
    "code": 0,
    "message": "操作成功",
    "data": [
        {
            "id": 1,
            "questionText": "当你要外出一整天，你会",
            "dimension": "EI",
            "sort": 1,
            "status": 1
        },
        {
            "id": 2,
            "questionText": "你通常",
            "dimension": "EI",
            "sort": 2,
            "status": 1
        }
      .....
}
```

------

### 提交 MBTI 测试答案

#### 基本信息

> 请求路径：`/mbti/submit`
> 请求方式：`POST`
> 接口描述：提交用户对 MBTI 测试题目的答案，并返回测试结果。

#### 请求参数

| 参数名称 | 说明     | 类型                | 是否必须 | 备注                     |
| -------- | -------- | ------------------- | -------- | ------------------------ |
| username | 用户名   | string              | 是       | 提交测评的用户名         |
| answers  | 答案列表 | Map<Integer,String> | 是       | 键为题目ID，值为选项文本 |

请求示例：

```
POST /mbti/submit?username=lili
{
    "1": "说去就去",
    "2": "较为随兴所至的人",
    "3": "以事实为主的课程",
    "4": "比较沉静或矜持",
    "5": "富于想象力的人",
    "6": "你的情感支配你的理智",
    "7": "凭兴所至行事",
    "8": "难于让人了解",
    "9": "合你心意",
    "10": "边做边找须做什么",
    "11": "顺其自然"
}
```

#### 响应数据

```
{
    "code": 0,
    "message": "操作成功",
    "data": {
        "id": 6,
        "username": "lili",
        "mbtiType": "INFP",
        "eScore": 0,
        "iScore": 2,
        "sScore": 1,
        "nScore": 1,
        "tScore": 0,
        "fScore": 1,
        "jScore": 1,
        "pScore": 5,
        "testTime": "2025-04-30T05:53:41.000+00:00"
    }
}
```

------

### 根据用户名查询测评记录

#### 基本信息

> 请求路径：`/mbti/records/{username}`
> 请求方式：`GET`
> 接口描述：根据用户名查询该用户所有的 MBTI 测评记录。

#### 请求参数

| 参数名称 | 说明   | 类型   | 是否必须 | 备注   |
| -------- | ------ | ------ | -------- | ------ |
| username | 用户名 | string | 是       | 用户名 |

请求示例：

```
GET /mbti/records/lili
```

#### 响应数据

```
{
    "code": 0,
    "message": "操作成功",
    "data": [
        {
            "id": 6,
            "username": "lili",
            "mbtiType": "INFP",
            "eScore": 0,
            "iScore": 2,
            "sScore": 1,
            "nScore": 1,
            "tScore": 0,
            "fScore": 1,
            "jScore": 1,
            "pScore": 5,
            "testTime": "2025-04-30T05:53:41.000+00:00"
        },
        {
            "id": 5,
            "username": "lili",
            "mbtiType": "INFP",
            "eScore": 0,
            "iScore": 2,
            "sScore": 1,
            "nScore": 1,
            "tScore": 0,
            "fScore": 1,
            "jScore": 1,
            "pScore": 5,
            "testTime": "2025-04-30T02:59:04.000+00:00"
        },
       ....
    ]
}
```

------

### 删除指定测评记录

#### 基本信息

> 请求路径：`/mbti/records/{recordId}`
> 请求方式：`DELETE`
> 接口描述：根据测评记录 ID 删除对应记录。

#### 请求参数

| 参数名称 | 说明       | 类型    | 是否必须 | 备注         |
| -------- | ---------- | ------- | -------- | ------------ |
| recordId | 测评记录ID | integer | 是       | 测评记录主键 |

请求示例：

```
DELETE /mbti/records/1
```

#### 响应数据

```
{
    "code": 0,
    "message": "操作成功",
    "data": true
}
```
## 5AI

### 根据用户简历分析用户画像

#### 基本信息

> 请求路径：`/ai/career/profile/{username}`
> 请求方式：`GET`
> 接口描述：根据用户的简历信息，生成详细的用户画像，输出为 JSON 格式。

#### 请求参数

| 参数名称 | 说明   | 类型   | 是否必须 | 备注                     |
| -------- | ------ | ------ | -------- | ------------------------ |
| username | 用户名 | string | 是       | 用户的唯一标识符         |
| chatId   | 会话ID | string | 否       | 用于维持对话上下文，可选 |

请求示例：

```
GET /ai/career/profile/张三?chatId=1
```

#### 响应数据

```
{
  "code": 0,
  "message": "查询成功",
  "data": {
    "basicInfo": {
      "name": "张三",
      "age": 25,
      "education": "本科",
      "yearsOfExperience": 3,
      "currentPosition": "后端开发工程师",
      "location": "北京"
    },
    "personality": {
      "traits": ["好奇心强", "善于沟通"],
      "strengths": ["快速学习新技术", "团队协作能力强"],
      "weaknesses": ["表达能力较弱"],
      "analysis": "根据简历内容，该用户具有良好的沟通能力和团队协作精神，虽然表达能力较弱，但在技术方面表现出色。"
    },
    "professionalBackground": {
      "industries": ["互联网"],
      "roles": ["后端开发工程师"],
      "keyProjects": [
        {
          "name": "电商平台开发",
          "role": "后端开发",
          "achievements": ["设计并实现了高并发的支付系统", "优化了数据库查询速度，提升了系统性能"]
        }
      ],
      "analysis": "用户具有丰富的电商平台开发经验，擅长后端开发，特别是在高并发系统优化方面有突出表现。"
    },
    "skills": {
      "technical": [
        {
          "name": "Java",
          "level": 0.9,
          "years": 3,
          "certifications": ["Java认证"]
        }
      ],
      "skillsummary": [
        {
          "dimension": "技术能力",
          "level": 0.8,
          "comment": "具有较强的后端开发能力，熟悉Java技术栈"
        }
      ],
      "soft": [
        {
          "name": "团队协作",
          "level": 0.9,
          "examples": ["在多个跨部门项目中担任协作角色"]
        }
      ],
      "analysis": "用户的技术能力强，尤其在后端开发方面，团队协作能力也非常突出。"
    },
    "careerPreferences": {
      "preferredRoles": ["后端开发工程师"],
      "preferredIndustries": ["互联网"],
      "workStyle": ["团队协作"],
      "analysis": "根据简历内容，用户的职业偏好为后端开发，且倾向于团队合作。"
    },
    "developmentAreas": {
      "immediate": [
        {
          "area": "系统设计能力",
          "priority": "高",
          "suggestions": ["参与更复杂的系统设计项目", "加强系统架构设计的学习"]
        }
      ],
      "longTerm": [
        {
          "area": "算法建模能力",
          "timeline": "1-2年",
          "suggestions": ["学习更深入的算法知识", "参与机器学习相关项目"]
        }
      ]
    },
    "marketPosition": {
      "currentLevel": "中级",
      "salaryRange": {"min": 12000, "max": 18000},
      "competitiveAdvantages": ["擅长系统优化，具备项目经验"],
      "marketDemand": "后端开发工程师市场需求大，尤其是对高并发系统优化有经验的工程师需求较为紧张"
    }
  }
}
```

------

### 根据用户简历生成职业规划与成长路径接口

#### 基本信息

> 请求路径：`/ai/career/analysis/{username}`
> 请求方式：`GET`
> 接口描述：根据用户简历信息生成个性化的职业规划和成长路径建议。

#### 请求参数

| 参数名称 | 说明   | 类型   | 是否必须 | 备注                     |
| -------- | ------ | ------ | -------- | ------------------------ |
| username | 用户名 | string | 是       | 用户的唯一标识符         |
| chatId   | 会话ID | string | 否       | 用于维持对话上下文，可选 |

请求示例：

```
GET /ai/career/analysis/john_doe?chatId=1
```

#### 响应数据

```
{
  "code": 0,
  "message": "查询成功",
  "data": {
    "careerPath": {
      "shortTerm": {
        "goals": ["掌握新的后端框架", "优化现有系统性能"],
        "timeline": "3-6个月",
        "suggestions": ["参加线上技术课程", "通过项目实践提高架构设计能力"],
        "requiredSkills": ["Spring框架", "数据库优化"],
        "actionableSteps": ["完成Spring Boot高级课程", "参与项目中的性能优化"]
      },
      "mediumTerm": {
        "goals": ["成为技术负责人", "独立设计系统架构"],
        "timeline": "1-2年",
        "suggestions": ["积累更多项目管理经验", "加强团队协作能力"],
        "requiredSkills": ["架构设计", "团队管理"],
        "actionableSteps": ["参与技术决策", "带领团队完成复杂项目"]
      },
      "longTerm": {
        "goals": ["成为CTO", "主导公司技术方向"],
        "timeline": "3-5年",
        "suggestions": ["提升领导力", "深入了解行业发展趋势"],
        "requiredSkills": ["技术领导力", "行业研究"],
        "actionableSteps": ["参加领导力培训", "关注技术趋势，提升行业洞察力"]
      },
      "futureVision": {
        "longTermImpact": "未来在技术领域中，用户将引领后端技术的创新，并可能成为行业中的顶尖专家。",
        "disruptiveTrends": ["云计算", "微服务架构"],
        "futureSkills": ["云平台技能", "微服务架构设计"],
        "crossDomainOpportunities": ["从后端向云计算领域转型", "从技术专家向产品负责人转型"]
      }
    },
    "learningPath": {
      "immediate": [
        {
          "skill": "Spring框架",
          "resources": ["Spring官方文档", "在线课程"],
          "estimatedTime": "3个月",
          "learningStyle": "在线课程"
        }
      ],
      "next3Months": [
        {
          "skill": "数据库优化",
          "resources": ["数据库优化指南", "项目实践"],
          "estimatedTime": "3个月",
          "learningStyle": "项目驱动学习"
        }
      ],
      "next6Months": [
        {
          "skill": "系统架构设计",
          "resources": ["架构设计书籍", "架构设计案例分析"],
          "estimatedTime": "6个月",
          "learningStyle": "案例学习"
        }
      ]
    },
    "opportunities": {
      "internal": [
        {
          "type": "潜在晋升机会",
          "description": "用户可以通过提升团队管理能力，成为技术主管。",
          "requirements": ["领导力", "跨部门合作经验"],
          "actionItems": ["参加领导力培训", "争取主导项目的机会"]
        }
      ],
      "external": [
        {
          "type": "行业跨界机会",
          "description": "用户可以向云计算领域转型，成为云架构专家。",
          "requirements": ["云平台经验", "系统架构设计"],
          "actionItems": ["学习云平台技术", "参与云计算项目"]
        }
      ]
    },
    "riskAnalysis": {
      "potentialRisks": [
        {
          "risk": "技术更新过快，可能跟不上发展",
          "impact": "高",
          "mitigation": ["持续学习新技术", "参与技术社区"]
        }
      ],
      "marketTrends": ["技术变革", "行业重塑"],
      "recommendations": ["学习新兴技术", "参与创新项目"]
    },
    "personalBranding": {
      "strategy": "用户应积极展示技术成果，参与行业分享，建立个人品牌。",
      "publicSpeakingOpportunities": ["行业大会", "技术分享会"],
      "personalBlog/Portfolio": "建立个人博客或作品集，展示技术能力和项目经验"
    },
    "workLifeBalance": {
      "timeEnergyManagement": {
        "suggestions": ["合理安排工作和休息时间", "学会时间管理"],
        "methods": ["使用时间管理工具", "设定每天的工作与休息时间"]
      },
      "mentalHealthAndStressManagement": {
        "suggestions": ["定
```