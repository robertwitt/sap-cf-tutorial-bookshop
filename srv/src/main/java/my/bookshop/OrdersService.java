package my.bookshop;

import java.util.List;
import java.util.stream.Collectors;

import com.sap.cloud.sdk.service.prov.api.*;
import com.sap.cloud.sdk.service.prov.api.annotations.*;
import com.sap.cloud.sdk.service.prov.api.exits.*;
import com.sap.cloud.sdk.service.prov.api.request.*;
import com.sap.cloud.sdk.service.prov.api.response.*;

import org.slf4j.*;

public class OrdersService {

	private static final Logger LOG = LoggerFactory.getLogger(OrdersService.class.getName());

	@BeforeRead(entity = "Orders", serviceName = "CatalogService")
	public BeforeReadResponse beforeReadOrders(ReadRequest req, ExtensionHelper h) {
		LOG.error("##### Orders - beforeReadOrders ########");
		return BeforeReadResponse.setSuccess().response();
	}

	@AfterRead(entity = "Orders", serviceName = "CatalogService")
	public ReadResponse afterReadOrders(ReadRequest req, ReadResponseAccessor res, ExtensionHelper h) {
		EntityData ed = res.getEntityData();
		EntityData ex = EntityData.getBuilder(ed).addElement("amount", 1000).buildEntityData("Orders");
		return ReadResponse.setSuccess().setData(ex).response();
	}

	@AfterQuery(entity = "Orders", serviceName = "CatalogService")
	public QueryResponse afterQueryOrders(QueryRequest req, QueryResponseAccessor res, ExtensionHelper h) {
		List<EntityData> dataList = res.getEntityDataList();
		List<EntityData> modifiedList = dataList.stream()
				.map(ed -> EntityData.getBuilder(ed).addElement("amount", 1000).buildEntityData("Orders"))
				.collect(Collectors.toList());
		return QueryResponse.setSuccess().setData(modifiedList).response();
	}

}