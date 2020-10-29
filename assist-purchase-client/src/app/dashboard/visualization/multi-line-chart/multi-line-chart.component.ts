import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import * as Highcharts from 'highcharts/highstock';

@Component({
  selector: 'app-multi-line-chart',
  templateUrl: './multi-line-chart.component.html',
  styleUrls: ['./multi-line-chart.component.css']
})
export class MultiLineChartComponent implements OnChanges {
  @Input() statisticsdata: any[];
  @Input() benchmarkdata: any[];
  
  chartObject: any;

  chartOptions: any = {
    rangeSelector: {
      enabled: true
    },
    title: {
      text: 'Monitor Sales Statistics'
    },
    scrollbar: {
      enabled: true,
    },
    xAxis: [{
      startOnTick: true,
      endOnTick: true,
    }],
    tooltip: {
      split: true,
    },
    series: [{
      tooltip: {
        valueDecimals: 2
      },
      type: 'line',
      name: 'Product Sale Stats',
      pointWidth: 5,
      data: [
      ]
    },
    {
      tooltip: {
        valueDecimals: 2
      },
      type: 'line',
      name: 'Benchmark',
      pointWidth: 5,
      data: [
      ]
    }
    ],
    time: {
      useUTC: true
    }
  };

  constructor() { }

  ngOnChanges(): void {

        this.chartOptions.series[0].data = this.statisticsdata;
        this.chartOptions.series[1].data = this.benchmarkdata;

        this.chartObject = Highcharts.stockChart("chart", this.chartOptions);

        this.chartObject.xAxis[0].setExtremes(
          Date.UTC(2020, 0, 1, 12),
          Date.UTC(2020, 9, 15, 12) 
        );
      }
  }
