import { NgModule } from '@angular/core';

import { DungeonsAndDatabasesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [DungeonsAndDatabasesSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [DungeonsAndDatabasesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class DungeonsAndDatabasesSharedCommonModule {}
