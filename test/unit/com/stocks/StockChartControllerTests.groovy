package com.stocks



import org.junit.*
import grails.test.mixin.*

@TestFor(StockChartController)
@Mock(StockChart)
class StockChartControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stockChart/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stockChartInstanceList.size() == 0
        assert model.stockChartInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stockChartInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stockChartInstance != null
        assert view == '/stockChart/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stockChart/show/1'
        assert controller.flash.message != null
        assert StockChart.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stockChart/list'

        populateValidParams(params)
        def stockChart = new StockChart(params)

        assert stockChart.save() != null

        params.id = stockChart.id

        def model = controller.show()

        assert model.stockChartInstance == stockChart
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stockChart/list'

        populateValidParams(params)
        def stockChart = new StockChart(params)

        assert stockChart.save() != null

        params.id = stockChart.id

        def model = controller.edit()

        assert model.stockChartInstance == stockChart
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stockChart/list'

        response.reset()

        populateValidParams(params)
        def stockChart = new StockChart(params)

        assert stockChart.save() != null

        // test invalid parameters in update
        params.id = stockChart.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stockChart/edit"
        assert model.stockChartInstance != null

        stockChart.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stockChart/show/$stockChart.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stockChart.clearErrors()

        populateValidParams(params)
        params.id = stockChart.id
        params.version = -1
        controller.update()

        assert view == "/stockChart/edit"
        assert model.stockChartInstance != null
        assert model.stockChartInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stockChart/list'

        response.reset()

        populateValidParams(params)
        def stockChart = new StockChart(params)

        assert stockChart.save() != null
        assert StockChart.count() == 1

        params.id = stockChart.id

        controller.delete()

        assert StockChart.count() == 0
        assert StockChart.get(stockChart.id) == null
        assert response.redirectedUrl == '/stockChart/list'
    }
}
