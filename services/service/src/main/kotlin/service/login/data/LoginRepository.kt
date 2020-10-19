package service.login.data

import service.login.data.model.LoginEntity
import service.model.UserModel

interface LoginRepository {

    suspend fun execute(user: UserModel): LoginEntity
}
