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

###  查询所有用户

#### 基本信息

> 请求路径：`/user/list`
> 请求方式：`GET`
> 接口描述：查询系统中所有用户。

#### 请求参数

无

请求示例：

```
GET /user/list
```

#### 响应数据

```
{
  "code": 0,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "username": "john_doe",
      "nickname": "小约翰"
    },
    {
      "id": 2,
      "username": "alice",
      "nickname": "爱丽丝"
    }
  ]
}
```

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

**基本信息**

- 请求路径：`/userprofile/{username}`
- 请求方式：`GET`
- 接口描述：该接口用于获取指定用户名的用户画像数据。

**请求参数**

- 请求参数格式：`application/json`

- ​

  | 参数名称 | 说明   | 类型   | 是否必须 | 备注                     |
  | -------- | ------ | ------ | -------- | ------------------------ |
  | username | 用户名 | string | 是       | 需要获取用户画像的用户名 |

**请求数据样例：**

```
lili
```

**响应数据**

- 响应数据类型：`application/json`

- ​

  | 名称    | 类型   | 是否必须 | 默认值 | 备注                   |
  | ------- | ------ | -------- | ------ | ---------------------- |
  | code    | number | 是       |        | 响应码，0-成功，1-失败 |
  | message | string | 否       |        | 提示信息               |
  | data    | object | 否       |        | 返回的用户画像数据     |

**响应数据样例：**

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