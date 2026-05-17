package com.imcys.bilibilias.shared

import com.imcys.bilibilias.data.repository.UserInfoRepository
import com.imcys.bilibilias.data.repository.VideoInfoRepository
import com.imcys.bilibilias.network.service.BILIBILIWebAPIService
import com.imcys.bilibilias.network.utils.WebiTokenUtils.updateWebiKey
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object DataProvider : KoinComponent {
    val userInfoRepository: UserInfoRepository by inject()
    val videoInfoRepository: VideoInfoRepository by inject()
    private val webApiService: BILIBILIWebAPIService by inject()
}
