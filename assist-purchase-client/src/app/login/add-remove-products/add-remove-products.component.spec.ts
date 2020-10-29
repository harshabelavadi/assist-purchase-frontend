import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRemoveProductsComponent } from './add-remove-products.component';

describe('AddRemoveProductsComponent', () => {
  let component: AddRemoveProductsComponent;
  let fixture: ComponentFixture<AddRemoveProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddRemoveProductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRemoveProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
