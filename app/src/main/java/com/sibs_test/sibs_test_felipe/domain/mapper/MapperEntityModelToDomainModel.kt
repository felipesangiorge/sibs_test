package com.sibs_test.sibs_test_felipe.domain.mapper

import com.sibs_test.sibs_test_felipe.domain.model_domain.DomainModel

interface MapperEntityModelToDomainModel
<EntityModel : com.sibs_test.sibs_test_felipe.database.model_entity.EntityModel?, DomainModel : com.sibs_test.sibs_test_felipe.domain.model_domain.DomainModel?> {

    fun mapFromEntity(type: EntityModel): DomainModel
}