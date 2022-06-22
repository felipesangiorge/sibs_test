package com.sibs_test.sibs_test_felipe.data.mapper

interface MapperNetworkModelToEntityModel
<NetworkModel : com.sibs_test.sibs_test_felipe.network.model_result.NetworkModel?, EntityModel : com.sibs_test.sibs_test_felipe.database.model_entity.EntityModel?> {

    fun mapFromNetwork(type: NetworkModel): EntityModel
}